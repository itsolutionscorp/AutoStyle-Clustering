 DNA =  Struct.new(:strand) do

  def hamming_distance(input)
    distance_calc(input)
  end

  private
  def distance_calc(input)
    strand.chars.zip(input.chars).count do |x,y|
      y && x != y
    end
  end
  
end
