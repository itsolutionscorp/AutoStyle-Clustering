class Squares
  @holder=0
  def self.new(input)
  @holder=input
  self
  end
  
  def self.square_of_sums()
  output = 0
  @holder.to_i.times do |i|
	output += (i+1)
	end
  output*output
  end
  
  def self.sum_of_squares()
  output = 0
  @holder.to_i.times{|i|output+=(i+1)*(i+1)}
  output
  end
  
  def self.difference()
  self.square_of_sums - self.sum_of_squares
  end
end
