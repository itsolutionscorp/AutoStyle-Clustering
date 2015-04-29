class Allergies
  TYPES = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats',
  ]

  def initialize bit_pattern
    @bit_pattern = bit_pattern
  end

  def allergic_to? allergy
    list.include? allergy
  end

  def list
    @list ||=
      TYPES.select.with_index do |_, index|
        0 != @bit_pattern & (2**index)
      end
  end
end
