class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, klass)
    @db[klass] += [name]
  end


  def grade(num)
   @db[num]
  end

  #first sort the keys
  #then use the keys to sort the names
  #then combine both of them to return a hash

  def sort
    Hash[sort_grades.zip(sort_names)]
  end

  def sort_names
    sort_grades.map do |k|
      @db[k].sort
    end
  end

  def sort_grades
    @db.keys.sort
  end
end
