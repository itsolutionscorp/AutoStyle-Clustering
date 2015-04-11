class Robot
  
  def initialize
    @name = ''
  end
  
  def name
    2.times {@name << ALPHA_ARR.sample}
    3.times {@name << NUM_ARR.sample.to_s}
    @name
  end

  def reset
    @name = ''
  end

  private

  NUM_ARR   = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] # (0..9).to_a
  ALPHA_ARR = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"] # ('A'..'Z').to_a
end
