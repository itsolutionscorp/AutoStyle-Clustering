class School
  
  def initialize
    @school = Hash.new([])
  end

  def add name, grade
    @school.has_key?(grade) ? @school[grade].push(name) : @school[grade] = [name]
  end

  def to_hash
    sort
    @school
  end

  def grade mark
    sort
    @school[mark]
  end

  private 
  def sort
    temp = Hash.new([])
    @school.keys.sort.each { |key| temp[key] = @school[key].sort }
    @school = temp
  end
end
