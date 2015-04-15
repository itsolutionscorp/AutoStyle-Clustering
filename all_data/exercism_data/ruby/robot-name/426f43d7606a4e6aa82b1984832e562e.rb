class Robot

  @@name_hash = {}
  
  def initialize
    @name = generate_name
  end

  def name; @name; end 

  def reset
    old_name = @name
    initialize
    @@name_hash.delete(old_name)
  end
 
  def generate_name
    begin
      candidate_name = generate_string 
    end while @@name_hash[:"#{candidate_name}"] != nil
    @@name_hash[:"#{candidate_name}"] = self
    candidate_name
  end

  def generate_string
    ('A'..'Z').to_a.shuffle.first(2).join + ('0'..'9').to_a.shuffle.first(3).join 
  end
end
