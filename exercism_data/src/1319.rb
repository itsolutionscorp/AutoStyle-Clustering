def compute x,y
  (x.chars.to_a.zip y.chars.to_a).inject(0) do
    |distance, element| element.first != element.last ? distance += 1 : distance += 0
  end
end

end