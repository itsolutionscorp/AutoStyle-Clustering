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
  }
  
  def initialize score
    @score = score
  end
  
  def allergic_to? allergen
    allergy_intersection(allergen) != 0 if ALLERGENS.include? allergen
  end
  
  def list
    @allergies ||= ALLERGENS.select{|allergen| allergic_to? allergen }
  end
  
  private
  def allergy_intersection allergen
    @score & allergen_score(allergen)
  end
  
  def allergen_score allergen
    2**ALLERGENS.find_index(allergen)
  end
end
