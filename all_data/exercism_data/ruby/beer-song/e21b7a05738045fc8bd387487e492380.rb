# BeerSong - solution for Exercism.io problem.
class BeerSong
  def verse(n)
    "#{n1(n).capitalize} bottle#{s(n)} of beer on the wall, "\
    "#{n1(n)} bottle#{s(n)} of beer.\n" \
    "#{l3(n)}"\
    "#{n2(n)} bottle#{s(n - 1)} of beer on the wall.\n"
  end

  def verses(n, m)
    (m..n).to_a.reverse.map { |v| verse(v) }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end

  private

  def n1(n)
    n == 0 ? 'no more' : n.to_s
  end

  def s(n)
    n == 1 ? '' : 's'
  end

  FMT_L3 = 'Take %s down and pass it around, '

  def l3(n)
    case n
    when 0
      'Go to the store and buy some more, '
    when 1
      format(FMT_L3, 'it')
    else
      format(FMT_L3, 'one')
    end
  end

  def n2(n)
    case n
    when 0
      '99'
    when 1
      'no more'
    else
      (n - 1).to_s
    end
  end
end
