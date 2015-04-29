class Words
  def initialize(input)
    @words = input.downcase.gsub(/\W/," ").split
  end


  def count
    @words.inject(Hash.new(0)) { |h, e| h[e] += 1 ; h }
  end
end
