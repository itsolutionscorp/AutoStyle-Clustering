class School
  
  def initialize
    @school = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @school[grade] << name
  end

  def grade(year)
    @school[year]
  end

  def sort
    @school.keys.sort.each_with_object({}) do |year, result|
      result[year] = grade(year).sort
    end
  end

  def db
    @school.clone
  end
end
