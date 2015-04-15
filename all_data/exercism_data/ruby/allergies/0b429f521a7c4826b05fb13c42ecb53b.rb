class Allergies

    ALLERGIES = {
      "eggs" =>           1,
      "peanuts" =>        2,
      "shellfish" =>      4,
      "strawberries" =>   8,
      "tomatoes" =>       16,
      "chocolate" =>      32,
      "pollen" =>         64,
      "cats" =>           128
    }

	def initialize(n)
    @n = n
    @allergies = []
	end


  def list

    ALLERGIES.values.reverse.each do |i|
      if @n-i >= 0
        @allergies << ALLERGIES.key(i)
        @n -= i
      end
    end

    @allergies.empty? ? (return []) : (return @allergies.reverse)

  end


  def allergic_to?(food)
    list.include?(food)
  end
end
