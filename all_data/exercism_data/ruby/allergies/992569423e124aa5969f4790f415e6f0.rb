class Allergies
  TABLE = [
    'cats',
    'pollen',
    'chocolate',
    'tomatoes',
    'strawberries',
    'shellfish',
    'peanuts',
    'eggs',
  ]

  def initialize(score)
    @score = score
  end

  def allergic_to?(item)
    list.include? item
  end

  def list
    TABLE.values_at(*allergic_indexes).reverse
  end

  private

  def allergic_indexes
    bitmask.map.with_index { |bit, index| index if bit == '1' }.compact
  end

  def bitmask
    @score.to_s(2).rjust(TABLE.length, '0').chars.to_a.last(TABLE.length)
  end
end
