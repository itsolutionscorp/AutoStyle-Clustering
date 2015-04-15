class Allergies
  DICT = { "eggs"=> 1,
           "peanuts"=> 2,
           "shellfish"=> 4,
           "strawberries"=> 8,
           "tomatoes"=> 16,
           "chocolate"=> 32,
           "pollen"=> 64,
           "cats"=> 128 }

  def initialize(n)
    @n = n
  end

  def list
    DICT.keys.select { |k| allergic_to?(k) }
  end

  def allergic_to?(food)
    a = DICT[food]
    @n & a == a
  end
end
