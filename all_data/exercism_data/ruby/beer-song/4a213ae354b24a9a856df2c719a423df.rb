class BeerSong
  def sing
    verses 99
  end
  
  def verses start_verse, end_verse = 0
    each_verse(start_verse, end_verse).each do |verse_number, song| 
      song << "#{verse(verse_number)}\n"
    end
  end
  
  def verse number
    "#{first_line number}#{second_line number}"
  end
  
  private
  
  def each_verse start_verse, end_verse = 0
    (end_verse..start_verse).to_a.reverse.each_with_object("")
  end
  
  def bottles number
    case number
    when 1
      "1 bottle"
    when 0
      "No more bottles"
    else
      "#{number} bottles"
    end
  end
  
  def first_line number
    "#{bottles number} of beer on the wall, " +
    "#{(bottles number).downcase} of beer.\n"
  end
  def second_line number
    case number
    when 1
      "Take it down and pass it around, " +
      "no more bottles of beer on the wall.\n"
    when 0
      "Go to the store and buy some more, " +
      "99 bottles of beer on the wall.\n"
    else
      "Take one down and pass it around, " +
      "#{bottles number-1} of beer on the wall.\n"
    end
  end
end
