<!-- Modal -->
  <div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalCenterTitle">New Folder</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form action="route_folder" method="GET">
        <div class="modal-body">
          <div class="row">
            <div class="col mb-3">
              <label for="nameWithTitle" class="form-label">Folder Name</label>
              <!-- for folder_id value if there is value display it else display 0 -->
              <input type="hidden" name="folder_id" value="<%= 0 %>">
              <input type="hidden" name='command' value='CREATE'/>
              <input
                type="text"
                id="folder_name"
                name="folder_name"
                class="form-control"
                placeholder="Enter Folder Name"
                required
                autofocus
              />
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
            Close
          </button>
          <button type="submit" class="btn btn-primary">Create</button>
        </div>
        </form>
      </div>
    </div>
  </div>
  <!-- Modal End -->