class Robot
  attr_reader :name

  POSSIBLE_NAMES = 676000
  @@name_db      = {}
  @@uniq_names   = 0

  def initialize
    @name = ""
    generate_uniq_name
    @@name_db.merge!( Hash[ [ [ @name, "active" ] ] ] )
  end

  def reset
    deprecate_name
    initialize
  end

  def deprecate_name
    @@name_db[ @name ] = "dead"
  end

  def generate_uniq_name
    begin
      random_name
    end while name_taken?
    @@uniq_names += 1
  end

  def random_name
    (1..2).each do |letter|
      @name << ("A".."Z").last( 26 - rand(25) ).first
    end
    (1..3).each do |number|
      @name << rand(9).to_s
    end
  end

  def name_taken?
    !@@name_db[ @name ].nil?
  end
end
