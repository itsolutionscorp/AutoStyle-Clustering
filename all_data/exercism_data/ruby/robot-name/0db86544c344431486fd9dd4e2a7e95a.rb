class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = FactoryNames.generate_name(self.name)
  end
end

module FactoryNames
  extend self

  def generate_name(current_name)
    remove_from_all_names(current_name) unless current_name.nil?
    unique_name
  end

  private

  def set_name_letters
    ("A".."Z").to_a.shuffle[0..1].join
  end

  def set_name_digits
    rand(1000).to_s.rjust(3, "0")
  end

  def create_name
    set_name_letters + set_name_digits
  end

  def unique_name
    @all_names ||= []
    name = create_name
    while @all_names.include?(name)
      name = create_name
    end
    add_to_all_names(name)
    name
  end

  def add_to_all_names(name)
    @all_names.push(name)
  end

  def remove_from_all_names(name)
    @all_names.delete(name)
  end
end
