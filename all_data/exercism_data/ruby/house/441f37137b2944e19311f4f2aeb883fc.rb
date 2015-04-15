class House
  def self.recite
    result = []

    tokens = [
      {
        :base => "malt", :verb => "lay"
      },
      {
        :base => "rat", :verb => "ate"
      },
      {
        :base => "cat", :verb => "killed"
      },
      {
        :base => "dog", :verb => "worried"
      },
      {
        :base => "cow with the crumpled horn", :verb => "tossed"
      },
      {
        :base => "maiden all forlorn", :verb => "milked"
      },
      {
        :base => "man all tattered and torn", :verb => "kissed"
      },
      {
        :base => "priest all shaven and shorn", :verb => "married"
      },
      {
        :base => "rooster that crowed in the morn", :verb => "woke"
      },
      {
        :base => "farmer sowing his corn", :verb => "kept"
      },
      {
        :base => "horse and the hound and the horn", :verb => "belonged to"
      }
    ]

    ending_phrase = 'house that Jack built'

    result << "This is the #{ending_phrase}."

    tokens.each_with_index do |token, index|
      result << ""

      result << "This is the #{token[:base]}"

      (1..index).to_a.reverse.each do |x|
        current_token = tokens[x]

        post_token = (x == 0 ? "in the" : "the")
        result << "that #{current_token[:verb]} #{post_token} #{tokens[x-1][:base]}"

      end

      result << "that #{tokens[0][:verb]} in the #{ending_phrase}."
    end
    result.join("\n") + "\n"
  end

end
