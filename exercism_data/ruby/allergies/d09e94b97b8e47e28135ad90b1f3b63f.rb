class Allergies
  attr_reader :allergy_number

  ALLERGY_SCORES = {
    cats: 128,
    pollen: 64,
    chocolate: 32,
    tomatoes: 16,
    strawberries: 8,
    shellfish: 4,
    peanuts: 2,
    eggs: 1,
  }

  def initialize(allergy_number)
    @allergy_number = allergy_number
  end

  def allergic_to?(allergy)
    list.include?(allergy)
  end

  def list
    number = allergy_number
    allergy_list = []
    ALLERGY_SCORES.each do |k,v|
      if number >= v
        puts "#{number} minus #{v}"
        number -= v
        allergy_list << k.to_s
      end
      break if number <= 0
    end
    allergy_list.reverse
  end

end
