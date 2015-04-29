class FoodChainSong
  TAGS = [
    {subject: "fly", phrase: "I don't know why she swallowed the fly. Perhaps she'll die."},
    {subject: "spider", phrase: "It wriggled and jiggled and tickled inside her."},
    {subject: "bird", phrase: "How absurd to swallow a bird!"},
    {subject: "cat", phrase: "Imagine that, to swallow a cat!"},
    {subject: "dog", phrase: "What a hog, to swallow a dog!"},
    {subject: "goat", phrase: "Just opened her throat and swallowed a goat!"},
    {subject: "cow", phrase: "I don't know how she swallowed a cow!"},
    {subject: "horse", phrase: "She's dead, of course!"}
  ]
  def verse verse_num
    first_line(verse_num) + second_line(verse_num) +
    middle_lines(verse_num) + last_line(verse_num)
  end

  def verses first, last
    first.upto(last).reduce("") do |song,verse_num|
      song << verse(verse_num) + "\n"
    end
  end

  def sing
    verses 1, 8
  end

  private
    def first_line verse_num
      "I know an old lady who swallowed a #{TAGS[verse_num-1][:subject]}.\n"
    end

    def second_line verse_num
      TAGS[verse_num-1][:phrase] + "\n"
    end

    def middle_lines verse_num
      if [1,8].include? verse_num
        ""
      else
        subjects(verse_num).each_cons(2).reduce("") do |song,(first,second)|
          song << "She swallowed the #{first} to catch the #{second}" +
          swallowed_line_end(first) + "\n"
        end
      end
    end

    def last_line verse_num
      [1,8].include?(verse_num) ? "" : "#{TAGS.first[:phrase]}\n"
    end

    def subjects verse_num
      TAGS.take(verse_num).map { |tag| tag[:subject] }.reverse
    end

    def swallowed_line_end subject
      subject == "bird" ? TAGS[1][:phrase].gsub("It"," that") : "."
    end
end
