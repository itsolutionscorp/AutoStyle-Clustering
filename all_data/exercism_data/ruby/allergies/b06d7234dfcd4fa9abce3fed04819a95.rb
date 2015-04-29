class Allergies
  ALLERGENS = %w{
    eggs
    peanuts 
    shellfish 
    strawberries 
    tomatoes 
    chocolate 
    pollen 
    cats
  }.each_with_object({}).with_index{|(allergen, list),i| list[allergen] = 2**i }
  
  def initialize score
    @score = score
  end
  
  def allergic_to? allergen
    allergy_intersection(allergen) != 0
  end
  
  def list
    @allergies ||= ALLERGENS.keys.select{|allergen| allergic_to? allergen }
  end
  
  private
  def allergy_intersection allergen
    @score & allergen_score(allergen)
  end
  
  def allergen_score allergen
    ALLERGENS[allergen] || 0
  end
end
