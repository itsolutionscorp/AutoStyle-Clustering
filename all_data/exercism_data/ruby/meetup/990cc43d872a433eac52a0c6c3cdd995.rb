Meetup = Struct.new(:month, :year) do
  Date::DAYNAMES.map(&:downcase).each do |day|
    -> name, start do
      define_method(name) do
        (start...start+7).lazy.map { |d|
          Date.new(year, month, d)
        }.find { |date|
          date.send(day + ??)
        }
      end
    end.instance_exec do
      call("#{day.sub(/day\z/, '')}teenth", 13)

      %w[first second third fourth].each_with_index do |nth, n|
        call("#{nth}_#{day}", 7*n+1)
      end

      call("last_#{day}", -7)
    end
  end
end
