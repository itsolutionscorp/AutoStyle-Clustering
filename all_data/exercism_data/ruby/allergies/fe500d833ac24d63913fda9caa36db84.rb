class Allergies
  List = %w{eggs peanuts shellfish strawberries tomatoes chocolate pollen cats}

  def initialize(num)
    number = num 
    @hash = Hash.new(false)
    8.downto(0).each do |i|
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
    List.map {|element| element if @hash[element] }.compact
  end
end
