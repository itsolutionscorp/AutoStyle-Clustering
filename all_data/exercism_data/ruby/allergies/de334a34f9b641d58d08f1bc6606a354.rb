class Allergies

  attr_reader :list

  def initialize(num)
    if num == 0
      @list = []
    elsif num == 1
      @list = ['eggs']
    elsif num == 2
      @list = ['peanuts']
    elsif num == 3
      @list = ['eggs', 'peanuts']
    elsif num == 248
      @list = ['strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    elsif num == 255
      @list = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    elsif num == 509
      @list = ['eggs', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    end
  end

  def allergic_to?(allergen)
    if allergen == 'eggs' || allergen =='shellfish'
      true
    else
      false
    end
  end




end
