class Robot

  attr_reader :name

  @@all_names = []

  def initialize
    @name = random_name
    @@all_names << @name
  end

  def reset
    @@all_names.delete(@name)
    @name = random_name
  end

  # private

  def random_name
    candidate = ""
    loop do
      candidate = gen_name [ 'a'..'z', 'a'..'z', '0'..'9', '0'..'9', '0'..'9' ]
      break unless @@all_names.include? candidate
    end
    candidate
  end

  def gen_name pat
    pat.each_with_object([]) { |p, a|
      v2 = p.to_a
      a << v2[Random.rand(v2.size)]
    }.join
  end


end
