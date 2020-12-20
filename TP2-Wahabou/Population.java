

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Population {

    private Individual[] individuals;
    private int genesPerPop;
    private Crosstype crosstype;
    private float mutationChance;

    /**
     * Representation of a population of pseudo-randomly generated individuals
     * @param popSize set the size of this population
     * @param genesPerPop sets the gene size of each individual in the pool
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(int popSize, int genesPerPop, Crosstype crosstype, float mutationChance)
    {
        this.individuals = new Individual[popSize];
        this.genesPerPop = genesPerPop;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
        for(int i=0; i<popSize; i++)
            this.individuals[i] = new Individual(genesPerPop);
    }

    /**
     * Representation of a population of pre-computed individuals
     * @param individuals an array of individuals
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(Individual[] individuals, Crosstype crosstype, float mutationChance)
    {
        assert individuals.length > 0;
        this.individuals = individuals;
        this.genesPerPop = individuals[0].getGenes().length;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
    }

    /**
     * Creates a new population using this generation's individuals
     * @return the newly generated population
     */
    public Population generateNewPopulation() 
    {
		int totalfitness=0;
		int indivchosen=0;
		int indiv1=0;
		int indiv2=0;
  

        if(this.crosstype == Crosstype.ROULETTE)
        {
        	
        	// On additionne toutes les fitness pour trouver un individu par roulette
            for(int i=0;i<individuals.length;i++)
            {
            	totalfitness+=individuals[i].getFitness();
            }
            if(totalfitness==0) {totalfitness++;}
            Random rand = new Random();
            rand.setSeed(new Date().getTime());
                indivchosen = rand.nextInt(totalfitness);
            for(int i=0;i<individuals.length;i++)
            {
            		//on parcours la liste des individus en soustrayant à notre fitness total la fitness de chaque individu , lorsque on arrive à 0 il s'agit de l'individu sélectionné
                	indivchosen=indivchosen-individuals[i].getFitness();
                	if(indivchosen<=0)
                	{indiv1=i;}
            }
            totalfitness=0;
            
            //On doit le refaire pour avoir un deuxième parent pour avoir des enfants
            for(int i=0;i<individuals.length;i++)
            {
            	totalfitness+=individuals[i].getFitness();
            }
            rand.setSeed(new Date().getTime());
            if(totalfitness==0) {totalfitness++;}
                indivchosen = rand.nextInt(totalfitness);
            for(int i=0;i<individuals.length;i++)
            {
                	indivchosen=indivchosen-individuals[i].getFitness();
                	if(indivchosen<=0)
                	{indiv2=i;}
            }
        }
        else{
            //ToDo generate using a TOURNOI crosstype
        }
        
        //Maintenant que l'on dispose de deux parents on peut former des enfants
        Random rand = new Random();
        rand.setSeed(new Date().getTime());
        Individual[] offsprings = reproduceIndividuals(individuals[indiv1],individuals[indiv2],rand.nextInt(genesPerPop));
        Population pop = new Population(offsprings,crosstype,mutationChance);
        return pop;
    }

    /**
     * Takes 2 individuals and create 2 children using their genes
     * @param firstParent the first selected individual
     * @param secondParent the second selected individual
     * @param crosspoint index of the crosspoint
     * @return an array of 2 individuals
     */
    public Individual[] reproduceIndividuals(Individual firstParent, Individual secondParent, int crosspoint)
          
    {
        Individual[] offsprings = new Individual[2];
		
        	// Implémentation du croisement à un point pour les deux parents et les deux enfants
        	int[] firstChildGenes = new int[genesPerPop];
        	int[] secondChildGenes = new int[genesPerPop];
        for(int i=0;i<genesPerPop;i++) {
        	if(i<crosspoint) {
        	firstChildGenes[i]=firstParent.getGenes()[i];
        	secondChildGenes[i]=secondParent.getGenes()[i];}
        	else {
        	firstChildGenes[i]=secondParent.getGenes()[i];
            secondChildGenes[i]=firstParent.getGenes()[i];
        	}
        }
 		offsprings[0] = new Individual(firstChildGenes);
 		offsprings[1] = new Individual(secondChildGenes);
		return offsprings;
    }

    @Override
    public String toString()
    {
        return "Population{" +
                "individuals=" + Arrays.toString(individuals) +
                ", genesPerPop=" + genesPerPop +
                '}';
    }
}
