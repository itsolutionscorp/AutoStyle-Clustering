class Robot
  def name
    @name ||= random_alpha_prefix + random_numeric_suffix
  end
  
  def reset
    @name = nil
  end
  
  private
  def random_alpha_prefix
    ('AA'..'ZZ').to_a.sample
  end
  
  def random_numeric_suffix
    ('000'..'999').to_a.sample
  end
end
