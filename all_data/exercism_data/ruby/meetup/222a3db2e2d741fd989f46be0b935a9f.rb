Meetup = Struct.new(:month, :year) do
  Date::DAYNAMES.map(&:downcase).each do |day|
    -> name, enum do
      define_method(name) do
        enum.lazy.map { |d|
          Date.new(year, month, d)
        }.find { |date|
          date.send(day + '?')
        }
      end
    end.instance_exec do
      call("#{day.sub(/day\z/, '')}teenth", 13 .. 19)

      %w[first second third fourth].zip(1..4).each do |nth, n|
        call("#{nth}_#{day}", 7*n-6 .. 7*n)
      end

      call("last_#{day}", -7 .. -1)
    end
  end
end
