class Robot
  
  @@num_to_alph = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
  @@rand_gen = Random.new
  attr_accessor :name
  
  def initialize
    @name = self.name_gen
  end
  
  def name_gen
    @@num_to_alph[@@rand_gen.rand(26)] + @@num_to_alph[@@rand_gen.rand(26)] + @@rand_gen.rand(9).to_s + @@rand_gen.rand(9).to_s + @@rand_gen.rand(9).to_s
  end
  
  def reset
    @name = self.name_gen
  end
  
end
