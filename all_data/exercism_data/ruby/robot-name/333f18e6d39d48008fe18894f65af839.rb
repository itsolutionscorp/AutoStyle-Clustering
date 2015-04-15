class Robot
  attr_reader :name
  @@names = Hash.new {|names, prefix| names[prefix] = [] }

  def initialize
    @name = generate_random
  end

  def reset
    @name = generate_random
  end

  private
  def generate_random
    prefix = make_prefix
    suffix = make_suffix(prefix)
    "#{prefix}#{suffix}"
  end

  def make_prefix
    characters = (0..25).map{|index| ('A'.bytes[0]+index).chr}
    choices = [Random.rand(25), Random.rand(25)]
    choices.collect{|c| characters[c]}.join
  end

  def make_suffix(prefix)
    chosen_suffix = nil
    begin
      chosen_suffix = "%03d" % Random.rand(999)
    end while @@names[prefix].include?(chosen_suffix)
    @@names[prefix] << chosen_suffix
    chosen_suffix
  end
end
