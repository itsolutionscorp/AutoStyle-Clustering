class Beer
  def sing(from, to=0)
    from.downto(to).map { |n| verse(n) + "\n" }.join
  end

  def verse(number)
    <<-VERSE.gsub(/^ +/, '')
    #{ stanza(number).capitalize } on the wall, #{ stanza number }.
    #{ ending number }, #{ stanza(number - 1) } on the wall.
    VERSE
  end

  private

  def stanza(number)
    number < 0 ?
      stanza(99) :
      "#{ count number } #{ bottles number } of beer"
  end

  def count(number)
    number < 1 ? 'no more' : number
  end

  def bottles(number)
    number == 1 ? 'bottle' : 'bottles'
  end

  def ending(number)
    number > 0 ?
      "Take #{ it number } down and pass it around" :
      "Go to the store and buy some more"
  end

  def it(number)
    number > 1 ? 'one' : 'it'
  end
end
