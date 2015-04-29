class House
  CONST1 = "the house that Jack built."
  SUBJ = ["malt", "rat", "cat", "dog"]
  VERB = ["lay in #{CONST1}", "ate", "killed", "worried"]

  def self.recite
    SUBJ.each_index.with_object(String.new) do |index, result|
      result << "This is #{CONST1}\n\n" if index.eql?(0)
      result << compose_verse(index) << "\n\n"
    end.chomp
  end

  def self.compose_verse(index)
    index.downto(0).with_object(String.new) do |i, result|
      if (index-i).eql?(0)
        result << "This is the #{SUBJ[index]}\nthat #{VERB[index]}"
      else
        result << " the #{SUBJ[i]}\nthat #{VERB[i]}"
      end
    end
  end
end
