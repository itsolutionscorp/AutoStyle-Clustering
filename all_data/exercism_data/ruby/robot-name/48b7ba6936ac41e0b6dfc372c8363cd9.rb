class Robot
  def initialize
    @name = reset
  end

  def name
    # string = []
    # letters = []
    # ("a".."z").each{|x| letters << x}
    # ("A".."Z").each{|x| letters << x}
    # 2.times{string << letters[rand(52)]}
    # 3.times{string << rand(0..9)}
    @name #= string.map{|x| x.to_s}.join("")
  end

  def reset
    string = []
    letters = []
    ("a".."z").each{|x| letters << x}
    ("A".."Z").each{|x| letters << x}
    2.times{string << letters[rand(52)]}
    3.times{string << rand(0..9)}
    @name = string.map{|x| x.to_s}.join("")

  end

end
