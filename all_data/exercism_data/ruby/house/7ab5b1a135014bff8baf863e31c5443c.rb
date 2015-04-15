class House

  WORDS = [
    { :name =>"house",:desc => "that Jack built.", :action => "lay in" },
    { :name =>"malt", :action => "ate" },
    { :name =>"rat", :action => "killed" },
    { :name =>"cat", :action => "worried" },
    { :name =>"dog", :action => "tossed" },
    { :name =>"cow", :desc => "with the crumpled horn", :action => "milked" },
    { :name =>"maiden", :desc => "all forlorn", :action => "kissed" },
    { :name =>"man", :desc => "all tattered and torn", :action => "married" },
    { :name =>"priest", :desc => "all shaven and shorn", :action => "woke" },
    { :name =>"rooster", :desc => "that crowed in the morn", :action => "kept" },
    { :name =>"farmer", :desc => "sowing his corn", :action => "belonged to" },
    { :name =>"horse", :desc => "and the hound and the horn", :action => "" },
  ]

  private_constant :WORDS

  def self.recite
    phrase.join "\n"
  end

  private

  def self.phrase
    (0...WORDS.size).each_with_object([]) do |i,result|
      result << first_line(i)
      (i-1).downto(0).each { |j| result << second_line(j) }
      result << ""
    end
  end

  def self.first_line index
    str = "This is the "
    str << [WORDS[index][:name], WORDS[index][:desc]].compact.join(" ")
  end

  def self.second_line index
    str = "that #{WORDS[index][:action]} the "
    str << [WORDS[index][:name], WORDS[index][:desc]].compact.join(" ")
  end

end
