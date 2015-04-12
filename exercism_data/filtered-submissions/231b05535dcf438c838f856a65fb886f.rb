def compute x,y
  (x.split("").zip y.split("")).inject(0) do
    |distance, element| element.first != element.last ? distance += 1 : distance += 0
  end
end

end