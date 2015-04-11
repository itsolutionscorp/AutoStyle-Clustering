class Robot
  def initialize(persistence: NamePersistence, generator: NameGenerator)
    self.persistence = persistence
    self.generator = generator
  end

  def name
    @name ||= generate_name
  end

  def reset
    self.name = nil
  end

  private

  attr_writer :name
  attr_accessor :persistence, :generator

  def generate_name
    temp_name = generator.build
    until persistence.add(temp_name)
      temp_name = generator.build
    end
    temp_name
  end
end

class NameGenerator < SimpleDelegator
  def self.build
    self.new(('A'..'Z').to_a.sample(2).join + rand.to_s[2,3])
  end
end

require 'set'
class NamePersistence
  def self.add(name)
    collection.add?(name)
  end

  def self.clear!
    @@collection = nil
  end

  private

  def self.collection
    @@collection ||= Set.new
  end
end
