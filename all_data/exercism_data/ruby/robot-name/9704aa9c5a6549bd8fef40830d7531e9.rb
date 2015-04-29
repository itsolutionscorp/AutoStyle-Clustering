class Robot
  attr_reader :name

  def initialize
    @name = make_name
  end

  def make_name
    "asimov-#{id_assemble}"
  end

  def reset
    @name = make_name
  end

  def id_assemble
    @id = String.new
    @id << random_letter + random_letter
    @id << random_number + random_number + random_number
  end

  def random_letter
    up_and_downcase_letters_append.sample
  end

  def random_number
    (0..9).to_a.sample.to_s
  end

  def up_and_downcase_letters_append
    @all_case = Array.new
    ("A".."Z").each {|letter| @all_case << letter}
    ("a".."z").each {|letter| @all_case << letter}
    @all_case
  end
end
