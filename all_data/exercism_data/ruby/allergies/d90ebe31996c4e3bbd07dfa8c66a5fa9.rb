class Allergies
  def initialize(allergic_score)
    @allergic_score = allergic_score
    @allergie_scores = [128, 64, 32, 16, 8, 4, 2, 1]
    @allergies = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']
    @allergies_array = []
    self.check_allergies
  end

  def allergic_to?(allergen)
    allergic = false
    @allergies_array.each do |allergy|
      if allergy == allergen
        allergic = true
      end
    end
    allergic
  end

  def check_allergies
    allergy_score = @allergic_score
    @allergies_array =[]
    while allergy_score > 255
      allergy_score -= 256
    end
    @allergie_scores.each_with_index do |score, index|
      if allergy_score >= score
        @allergies_array << @allergies[index]
        allergy_score -= score
      end
    end
  end



  def list
    @allergies_array.reverse
  end

end
