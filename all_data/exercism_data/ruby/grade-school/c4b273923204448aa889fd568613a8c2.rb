class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade_level)
    ensure_grade_exists(grade_level)

    @db[grade_level] << name

    # Don't return the array - it's not required, and gives an internal reference to external code
    nil
  end

  def grade(grade_level)
    ensure_grade_exists(grade_level)
    
    # Clone the array to avoid giving an internal reference to external code
    @db[grade_level].clone
  end

  def sort
    sorted = {}
    @db.keys.sort.each {|grade_level| sorted[grade_level] = @db[grade_level].sort }
    
    # Don't need to worry about cloning this one - it's a copy already.
    sorted
  end

  private

  def ensure_grade_exists(grade_level)
    @db[grade_level] = [] unless @db.has_key? grade_level
  end
end
