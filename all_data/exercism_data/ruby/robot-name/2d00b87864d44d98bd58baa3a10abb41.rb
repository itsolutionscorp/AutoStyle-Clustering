require 'set'
class Robot
    
  attr_accessor :name
  
  @@existing_names = Set.new
  
  def initialize
    @name = generate_name
  end
  
  # We need to delete the old robot name from the set and generate a new one
  def reset
    @@existing_names.delete(@name)
    @name = generate_name
  end
  
  private
  
  #convenience method for viewing stored Robot names
  def self.stored_values
    @@existing_names
  end
  
  #recrusively generate a name until one is found not in the given set and append name to set.
  def generate_name
    name = ("A".."Z").to_a.sample(2).join("") + (100..999).to_a.sample.to_s
    if Robot.stored_values.include?(name)
      self.generate_name
    else
      @@existing_names << name
      name
    end
  end  
end
