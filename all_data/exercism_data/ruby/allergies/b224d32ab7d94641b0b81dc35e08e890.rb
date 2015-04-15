class Allergies
  ALLERGENS = %w(eggs peanuts shellfish strawberries
                 tomatoes chocolate pollen cats)

  def initialize(score)
    @score = score
  end

  def list
    @list ||= list_from allergy_hash
  end

  def allergic_to?(allergen)
    allergy_hash[allergen] == 1
  end

  def allergy_hash
    @allergy_hash ||= hash_from @score
  end

  private
  def hash_from(score)
    i = 0
    ALLERGENS.each_with_object({}) do |allergen, allergies|
      allergies[allergen] = (score >> i) & 1
      i += 1
    end
  end

  def list_from(hash)
    ALLERGENS.select { |allergen| hash[allergen] == 1 }
  end
end
