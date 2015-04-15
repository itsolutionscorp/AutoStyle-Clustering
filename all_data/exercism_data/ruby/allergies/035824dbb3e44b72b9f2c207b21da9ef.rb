class Allergies
  def initialize(num)
    @num = num
  end

  def allergic_to?(allergen)
    binary_string = num_to_binary(@num)
    return true if binary_string[-1] == "1" && allergen == "eggs"
    return true if binary_string[-2] == "1" && allergen == "peanuts"
    return true if binary_string[-3] == "1" && allergen == "shellfish"
    return true if binary_string[-4] == "1" && allergen == "strawberries"
    return true if binary_string[-5] == "1" && allergen == "tomatoes"
    return true if binary_string[-6] == "1" && allergen == "chocolate"
    return true if binary_string[-7] == "1" && allergen == "pollen"
    return true if binary_string[-8] == "1" && allergen == "cats"
  end

  def list
    binary_string = num_to_binary(@num)
    allergen_list = []
    allergen_list << "eggs"          if binary_string[-1] == "1"
    allergen_list << "peanuts"       if binary_string[-2] == "1"
    allergen_list << "shellfish"     if binary_string[-3] == "1"
    allergen_list << "strawberries"  if binary_string[-4] == "1"
    allergen_list << "tomatoes"      if binary_string[-5] == "1"
    allergen_list << "chocolate"     if binary_string[-6] == "1"
    allergen_list << "pollen"        if binary_string[-7] == "1"
    allergen_list << "cats"          if binary_string[-8] == "1"
    return allergen_list
  end

  def num_to_binary(num)
    @num.to_s(2).rjust(8, "0")
  end
end
