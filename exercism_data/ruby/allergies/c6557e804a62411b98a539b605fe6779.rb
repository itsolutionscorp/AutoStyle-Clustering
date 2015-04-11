class Allergies
  LIST = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]

  def initialize(score)
    @score = score
  end

  def list
    LIST.select { |item, index| allergic_to?(item) }
  end

  def allergic_to?(item)
    index = LIST.index(item)
    @score & (2 ** index) != 0
  end
end
