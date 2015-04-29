class Allergies

  ALLERGY_POINTS_HASH = {
    128 => "cats",
    64 =>"pollen",
    32 => "chocolate", 
    16 => "tomatoes",
    8 => "strawberries",
    4 => "shellfish",
    2 => "peanuts",
    1 => "eggs"
  }

  ALLERGIES_HASH = {
    "cats" => 128,
    "pollen" => 64,
    "chocolate" => 32,
    "tomatoes" => 16,
    "strawberries" => 8,
    "shellfish" => 4,
    "peanuts" => 2,
    "eggs" => 1
  }

  def initialize(allergy_num)
    @allergy_num = allergy_num
  end

  def allergic_to?(allergen)
    if ALLERGIES_HASH[allergen] <= @allergy_num
      return true 
    else
      return false
    end
  end

  def list
    allergies_list = []
    ALLERGY_POINTS_HASH.each do |key, value|
      if @allergy_num >= key
        @allergy_num = @allergy_num - key
        allergies_list << value
      end
    end
    allergies_list.reverse
  end

end
