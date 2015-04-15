class Robot

  attr_accessor :name

  def initialize()
    @name = generate_random_unique_name
  end

  def reset
    @name = generate_random_unique_name
  end





  private

  MATCHING_REGEX_d_ARR = ((0..9).to_a.map{|digit|digit.to_s}).flatten
  MATCHING_REGEX_w_ARR = ([] << ('A'..'Z').to_a << ('a'..'z').to_a << (0..9).to_a.map{|digit|digit.to_s} << ['_']).flatten
  
  @@names_bestowed_upon_robots = []
  

  def generate_random_unique_name
    name = nil
    while (name == nil)  || ( name_unique_among_robots?(name) == false )
      name = generate_random_name
    end
    return name
  end

  def generate_random_name
    name = ''
    name += MATCHING_REGEX_w_ARR.shuffle[0]
    name += MATCHING_REGEX_w_ARR.shuffle[0]
    name += MATCHING_REGEX_d_ARR.shuffle[0]
    name += MATCHING_REGEX_d_ARR.shuffle[0]
    name += MATCHING_REGEX_d_ARR.shuffle[0]
  end

  def name_unique_among_robots?(name_to_test)
    if @@names_bestowed_upon_robots.include?(name_to_test) then return false
    else return true 
    end
  end


  # The unique name bonus requirement did make my code better, in that it
  # reminded me to make all of the non-public facing methods and the
  # constant private. But I'm not sure what else the authore were
  # hinting at with this question.


end
