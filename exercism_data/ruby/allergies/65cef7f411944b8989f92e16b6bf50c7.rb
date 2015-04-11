class Allergies
  List = %w{eggs peanuts shellfish strawberries tomatoes chocolate pollen cats}

  def initialize(number)
    @hash = {}
    # log(1,2) = 0 so this guards against log(0,2) which is not defined
    start = Math::log([number,1].max, 2).floor
    start.downto(0).each do |i|
      if number / (2 ** i) == 1
        number -= 2 ** i
        @hash[List[i]] = true
      end
    end
  end

  def allergic_to?(type)
    @hash[type]
  end

  def list
    List.select { |element| @hash[element] }
  end
end
