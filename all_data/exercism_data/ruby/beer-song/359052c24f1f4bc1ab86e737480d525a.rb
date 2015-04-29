class Beer
  def sing(from, to=0)
    from.downto(to).map { |n| verse(n) + "\n" }.join
  end

  def verse(num)
    <<-VERSE.gsub(/^\s+/, '')
    #{ stanza(num).capitalize } on the wall, #{ stanza num }.
    #{ ending num }, #{ stanza(num - 1) } on the wall.
    VERSE
  end

  private

  def stanza(num)
    num < 0 ?  stanza(99) : "#{ number num } #{ bottles num } of beer"
  end

  def number(num)
    num < 1 ? 'no more' : num
  end

  def bottles(num)
    num == 1 ? 'bottle' : 'bottles'
  end

  def ending(num)
    num > 0 ?
      "Take #{ it num } down and pass it around" :
      "Go to the store and buy some more"
  end

  def it(num)
    num > 1 ? 'one' : 'it'
  end
end
