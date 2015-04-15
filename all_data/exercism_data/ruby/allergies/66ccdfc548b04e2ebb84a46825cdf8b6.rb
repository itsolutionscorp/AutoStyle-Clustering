class Allergies

  attr_reader :list

  def initialize(score)
    @list = []
    compute_allergies(score)
    list.reverse!
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  private

  def compute_allergies(score)
    ALLERGENS.each do |allergen, s|
      div, mod = score.divmod ALLERGENS[allergen]
      if div > 0
        list << allergen.to_s
        compute_allergies(mod)
        break
      end
    end
  end

  ALLERGENS =
  {
    cats: 128,
    pollen: 64,
    chocolate: 32,
    tomatoes: 16,
    strawberries: 8,
    shellfish: 4,
    peanuts: 2,
    eggs: 1,
  }

end
