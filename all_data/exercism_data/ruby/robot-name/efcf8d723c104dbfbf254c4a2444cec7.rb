class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    FactoryNames.remove_from_all_names(self.name) unless self.name.nil?
    @name = FactoryNames.unique_name
    FactoryNames.add_to_all_names(@name)
  end
end

module FactoryNames
  def self.set_name_letters
    ("A".."Z").to_a.shuffle[0..1].join
  end

  def self.set_name_digits
    rand(1000).to_s.rjust(3, "0")
  end

  def self.create_name
    set_name_letters + set_name_digits
  end

  def self.unique_name
    @all_names ||= []
    name = create_name
    while @all_names.include?(name)
      name = create_name
    end
    return(name)
  end

  def self.add_to_all_names(name)
    @all_names.push(name)
  end

  def self.remove_from_all_names(name)
    @all_names.delete(name)
  end
end
