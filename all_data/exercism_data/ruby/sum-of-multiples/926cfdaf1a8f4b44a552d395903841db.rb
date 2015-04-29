class SumOfMultiples 

  @@multipliers = [3,5]

  def self.new(*args)
    raise "No arguments given..I need some!" if  args.nil? || args.empty?
    @@multipliers = args 
    SumOfMultiples
  end

  def self.to(upto)
    multis = (0..upto-1).select{|num| @@multipliers.any?{|multi| num % multi == 0}}
    @@multipliers = [3,5]
    multis.inject{|sum, number| sum += number}
  end
  
end
