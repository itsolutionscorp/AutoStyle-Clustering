class Binary
  def initialize(binary)
    @binary = binary
    @places = binary_to_places
  end

  def to_decimal
    return 0 if @binary !~ /^[0-9]+$/ 
    binary_to_places.inject(0) {|sum,num| sum + num}
  end

  def binary_to_places
    places = []
    @binary.chars.each_with_index do |num, i|
      places << 2**(@binary.length - i - 1) if num.to_i == 1
    end
    places
  end
end
