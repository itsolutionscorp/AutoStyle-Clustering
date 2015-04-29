class ValueError < ArgumentError; end

module ArrayNearby
  refine Array do
    def nearby(i, j)
      (slice i - 1, 3 || []).map { |a| a[j - 1, 3] }
    end
  end
end

module Board
  using ArrayNearby

  def self.transform(rows)
    fail ValueError unless valid?(rows)
    rows.map.with_index do |row, i|
      row.gsub(/./).with_index do |c, j|
        c.sub(' ') do
          rows.nearby(i, j).join.count('*').nonzero? || ' '
        end
      end
    end
  end

  def self.valid?(rows)
    w = rows.first.length - 2
    h = '+' + '-' * w + '+'
    rows == [h, *rows.grep(/\A\|[ *]{#{w}}\|\z/), h]
  end
end
