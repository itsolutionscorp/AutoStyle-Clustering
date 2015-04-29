class School

  attr_reader :db

  def initialize
    @db ||= Hash.new
  end

  def add(name, key)
    @db[key] = [] unless @db[key]
    @db[key] << name
  end

  def grade(num)
    @db[num] ||= []
  end

  def sort
    Hash[@db.sort].select { |key, name| name.sort! }
  end

end
