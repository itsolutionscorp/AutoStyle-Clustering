class House

  WORDS = {
    1 => { :name =>"house",:desc => "that Jack built.", :action => "lay in" },
    2 => { :name =>"malt", :action => "ate" },
    3 => { :name =>"rat", :action => "killed" },
    4 => { :name =>"cat", :action => "worried" },
    5 => { :name =>"dog", :action => "tossed" },
    6 => { :name =>"cow", :desc => "with the crumpled horn", :action => "milked" },
    7 => { :name =>"maiden", :desc => "all forlorn", :action => "kissed" },
    8 => { :name =>"man", :desc => "all tattered and torn", :action => "married" },
    9 => { :name =>"priest", :desc => "all shaven and shorn", :action => "woke" },
    10 => { :name =>"rooster", :desc => "that crowed in the morn", :action => "kept" },
    11 => { :name =>"farmer", :desc => "sowing his corn", :action => "belonged to" },
    13 => { :name =>"horse", :desc => "and the hound and the horn", :action => "" },
  }

  private_constant :WORDS

  def self.recite
    phrase.join "\n"
  end

  private

  def self.phrase
    [].tap do |result|
      WORDS.each_with_object([]) do |(i,v), temp|
        result << first_line(i)
        temp.reverse.each {|j| result << second_line(j) }
        temp << i 
        result << ""
      end
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
