##Hamming Distance (http://exercism.io/exercises/ruby/hamming/readme)
##John Youngblood
##1/26/2015
class Hamming
  def self.compute(arg1, arg2)
    hammDis = 0
    arg1.length.times{|i| hammDis+=1 unless arg1[i] == arg2[i]}
    hammDis
  end
end
