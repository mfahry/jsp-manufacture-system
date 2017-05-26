function open_container()
{
    var size = document.getElementById('mysize').value;
    var content = '<form role="form"><div class="form-group"><label for="exampleInputEmail1">Email address</label><input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email"></div><div class="form-group"><label for="exampleInputPassword1">Password</label><input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"></div><div class="form-group"><label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile"><p class="help-block">Example block-level help text here.</p></div><div class="checkbox"><label><input type="checkbox"> Check me out</label></div><button type="submit" class="btn btn-default">Submit</button></form>';
    var title = 'My dynamic modal dialog form with bootstrap';
    var footer = '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button><button type="button" class="btn btn-primary">Save changes</button>';
    setModalBox(title, content, footer, size);
    $('#myModal').modal('show');
}
function setModalBox(title, content, footer, $size)
{
    document.getElementById('modal-bodyku').innerHTML = content;
    document.getElementById('myModalLabel').innerHTML = title;
    document.getElementById('modal-footerq').innerHTML = footer;
    if ($size == 'large')
    {
        $('#myModal').attr('class', 'modal fade bs-example-modal-lg')
                .attr('aria-labelledby', 'myLargeModalLabel');
        $('.modal-dialog').attr('class', 'modal-dialog modal-lg');
    }
    if ($size == 'standart')
    {
        $('#myModal').attr('class', 'modal fade')
                .attr('aria-labelledby', 'myModalLabel');
        $('.modal-dialog').attr('class', 'modal-dialog');
    }
    if ($size == 'small')
    {
        $('#myModal').attr('class', 'modal fade bs-example-modal-sm')
                .attr('aria-labelledby', 'mySmallModalLabel');
        $('.modal-dialog').attr('class', 'modal-dialog modal-sm');
    }
}