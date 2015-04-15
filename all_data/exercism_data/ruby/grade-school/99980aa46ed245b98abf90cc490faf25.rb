class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add( name, grade_number )
    if name.class == String && grade_number.class == Fixnum
      add_grade( grade_number ) unless grade_exist?( grade_number )
      @db[ grade_number ] <<  name
    end
  end

  def add_grade( number )
    new_grade = Hash[ [ [ number, [] ] ] ]
    @db.merge!( new_grade )
  end

  def grade( number )
    @db[ number ] || []
  end

  def grade_exist?( number )
    !@db[ number.to_i ].nil?
  end

  def keys_need_sorted?( arg )
    arg.keys.to_a == arg.keys.to_a.sort
  end
  
  def sort
    db_sorted = @db.dup
    Hash[ db_sorted.to_a.sort ].each { |key, value| value.sort! }
  end
end
